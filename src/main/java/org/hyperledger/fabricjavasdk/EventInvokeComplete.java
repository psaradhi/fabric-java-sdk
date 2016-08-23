
// This is the object that is delivered as the result with the "complete" event
// from a Transaction object for a **invoke** operation.
// TODO: This class may change once the real event processing is added.
class EventInvokeComplete {
    constructor(public result?:any){};
}
