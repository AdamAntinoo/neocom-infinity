import { MiningOperationsUseCaseInput } from 'application/use-cases/mining-operation/CapsuleerMiningOperationsUseCase'
import { EsiToken } from './EsiToken.interface'

export class MiningOperationsUseCaseInputConstructor {
	public construct(jwt: string, token: EsiToken): MiningOperationsUseCaseInput {
		const instance: MiningOperationsUseCaseInput = {
			jwt: jwt,
			token: token,
			capsuleerId: this.extractCapsuleerId(token.sub),
		}
		return instance
	}
	private extractCapsuleerId(subject: string): number {
		const id: number = Number(subject.split(':')[2])
		return id
	}
}
