
// This is the object that is delivered as the result with the "complete" event
// from a Transaction object for a **deploy** operation.
// TODO: This class may change once the real event processing is added.
export class EventDeployComplete {
    constructor(public uuid:string, public chaincodeID:string, public result?:any){};
}
