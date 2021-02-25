// - DOMAIN
import { EsiNode } from "./EsiNode.esi"

export class EsiGroup extends EsiNode {
    private groupId: number
    public groupName: string
    public groupCategoryId: number

    protected decode (): void {}
}
