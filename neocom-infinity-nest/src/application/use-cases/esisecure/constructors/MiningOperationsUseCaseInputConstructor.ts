import { MiningOperationsUseCaseInput } from '@App/use-cases/esisecure/CapsuleerMiningOperationsUseCase'
import { EsiToken } from '../../../domain/EsiToken.interface'

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
