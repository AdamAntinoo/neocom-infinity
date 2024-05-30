import { Option } from '@sniptt/monads'

export abstract class ITokenAuthentication {
	public abstract validateToken(token: string): boolean
	public abstract checkCapsuleer(capsuleerId: number): boolean
	public abstract getCurrentToken(): Option<any>
}
