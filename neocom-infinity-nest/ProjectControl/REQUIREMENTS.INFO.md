# NEOCOM Nest Backend Requirements and Notes
## Mining Sessions
Mining sessions are based on the detection of Ore assets change during time. During a mining operation the number of Ore items will be increased 
and the system can detect ehant this increase has started and when has stopped to identify a mining operation.
This feature is based on a new service the is the DeltaCalculator.

### Delta Calculator
The delta calculator is created to be used on mining operations. This means that has some limitations. The main one is that is should only
be applied to Ore items. All other items should be removed from the time line comparison. The scond and also important is that there should
be a timing data recovery process that will be used to detect the mining operation itself and to calculate the volume of items
created during the operation.
There is also a third requirement and it is that negative or 0 asset stocks should be removed from the delta calculation
since the mining operation is about positive actions. Negative delta stacks means that the user has destroyed or converted
some stacks into minerals and are not to be taken on account for delta calculations.

### Delta Calculator Limitations
A key limitation that should be researched is the case that the user packs a set of resourcces innto a new unique resource with
the sum of the quantities. This poses a problem on the assets calculations since negative elements are being removed from the 
calculations. The destroyed stacks will not be accounted on the mining operation while the new merge stack will so the calculation
will be wrong since part of the new stack was added before when created.

### Delta Calculator Alternatives
Instead using delta calculations for the final result the delta are used for intermediate
result during the operation. When the operation completed a full Ore asset compariso from the start set of assets to the last set of assets is done to do a real net comprison of the
asset accounting. This solution only leaves the limitation of Ore asset destruction
by conversion to Minerals but solves the mergin and consolidation os stacks.
