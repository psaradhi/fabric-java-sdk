
// This is the object that is delivered as the result with the "submitted" event
// from a Transaction object for a **deploy** operation.
class EventDeploySubmitted {
    // The transaction ID of a deploy transaction which was successfully submitted.
    constructor(public uuid:string, public chaincodeID:string){};
}
