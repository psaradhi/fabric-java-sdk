
// This is the data that is delivered as the result with the "error" event
// from a Transaction object for any of the following operations:
// **deploy**, **invoke**, or **query**.
class EventTransactionError {
    public msg:string;
    // The transaction ID of an invoke transaction which was successfully submitted.
    constructor(public error:any){
       if (error && error.msg && isFunction(error.msg.toString)) {
          this.msg = error.msg.toString();
       } else if (isFunction(error.toString)) {
          this.msg = error.toString();
       }
    };
}
