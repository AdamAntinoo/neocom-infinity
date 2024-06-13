declare namespace ProcessedBlueprintsUseCase {
	export type Input = ProcessedBlueprintsUseCaseInput
	export type Output = any[]
}
export interface ProcessedBlueprintsUseCaseInput {
	pilotId
}
export class ProcessedBlueprintsUseCase{

	public execute ( input : ProcessedBlueprintsUseCase.Input) : ProcessedBlueprintsUseCase.Output{
// - Get the data from Redis



	}
	
}
