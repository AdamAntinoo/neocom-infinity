import { ITokenAuthentication } from "./ITokenAuthentication.port";

export abstract class AuthenticationServicesPort {
    abstract authentication: ITokenAuthentication
}
