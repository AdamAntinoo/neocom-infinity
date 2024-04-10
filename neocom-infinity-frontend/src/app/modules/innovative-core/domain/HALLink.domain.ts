import { HALResolver } from "@app/services/HALResolver.service"
import { HALResolverService } from "./HALResolver.service"

export interface HALReference {
    readonly rel: string
    readonly href: string
}
export class HALLink<T> implements HALReference {
    private downloaded: boolean = false
    public rel: string
    public href: string
    private resolver: HALResolverService
    private factory: any // This is the factory constructor for T instances
    public target: T // The Instance when resolved. If undefined then the link is pending resolution
    private link: Promise<T>

    constructor(targetType: { new(values: object): T }) {
        this.factory = targetType
    }

    public setContents(contents: HALReference): HALLink<T> {
        this.rel = contents.rel
        this.href = contents.href
        return this
    }
    /** @deprecated */
    public typeCast(values: any): T {
        this.target = new this.factory(values)
        this.downloaded = true
        return this.target
    }
    // - G E T T E R S   &   S E T T E R S
    public isResolved(): boolean {
        return this.downloaded
    }
    public isDownloaded(): boolean {
        return this.downloaded
    }
    /** @deprecated */
    public getRelation(): string {
        return this.rel
    }
    public getHref(): string {
        return this.href
    }
    /** @deprecated */
    public getTarget(): T {
        return this.target
    }
    /** @deprecated */
    public getLink(): Promise<T> {
        return this.link
    }
    public resolve(): T {
        if (this.isResolved()) return this.target
        else this.link.then((target: T) => {
            this.target = target
            return this.target
        })
    }

    public static Builder = class Builder<T> {
        public halLink: HALLink<T>
        private target

        constructor(targetType: { new(values: object): T }) {
            this.halLink = new HALLink(targetType)
            this.target = targetType
        }
        public withResolver(resolver: HALResolverService): Builder<T> {
            this.halLink.resolver = resolver
            return this
        }
        public withReference(reference: string): Builder<T> {
            this.halLink.href = reference
            return this
        }
        public build(): HALLink<T> {
            if (undefined == this.halLink.resolver) throw new Error('Mandatory field "resolver" not defined.')
            if (undefined == this.halLink.href) throw new Error('Mandatory field "reference" not defined.')
            // Create and fire the Promise so the next time it is used it will be fulfilled.
            this.halLink.link = this.halLink.resolver.resolve<T>(this.halLink, new (this.target))
            this.halLink.link.then((result: T) => {
                this.halLink.target = result
            })
            return this.halLink
        }
    }
}
