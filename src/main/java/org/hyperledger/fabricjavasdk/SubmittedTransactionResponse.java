
// This is the data that is delivered as the result with the 'submitted' event
// from a TransactionContext object.
interface SubmittedTransactionResponse {
    // The transaction ID of a transaction which was successfully submitted.
    uuid:string;
}
