export interface UnsecuredProxyPort {
    apiv3_GetUnsecuredLink<T>(link: string): Promise<T>
}
