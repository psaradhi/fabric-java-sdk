
// This is the data that is delivered as the result with the "submitted" event
// from a Transaction object for an **invoke** operation.
export class EventInvokeSubmitted {
    // The transaction ID of an invoke transaction which was successfully submitted.
    constructor(public uuid:string){};
}
