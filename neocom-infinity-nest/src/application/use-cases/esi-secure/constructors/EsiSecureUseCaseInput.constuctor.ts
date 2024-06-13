import { EsiToken } from '@Infra/adapter/security/EsiToken.interface'
import { IUseCaseInput } from './UsecaseInput.interface'

export class EsiSecureUseCaseInputConstructor<T extends IUseCaseInput> {
	public construct(jwt: string, token: EsiToken): T {
		const instance = {
			jwt: jwt,
			token: token,
			capsuleerId: this.extractCapsuleerId(token.sub),
		}
		return instance as T
	}
	private extractCapsuleerId(subject: string): number {
		const id: number = Number(subject.split(':')[2])
		return id
	}
}
