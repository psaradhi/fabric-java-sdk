
/**
 * Deploy request.
 */
interface DeployRequest extends TransactionRequest {
    // The local path containing the chaincode to deploy in network mode.
    String chaincodePath;
    // The name identifier for the chaincode to deploy in development mode.
    String chaincodeName;
}
