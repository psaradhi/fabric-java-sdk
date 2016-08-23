
/**
 * Invoke or query request.
 */
interface InvokeOrQueryRequest extends TransactionRequest {
    // Optionally pass a list of attributes which can be used by chaincode to perform access control
    attrs?:string[];
}
