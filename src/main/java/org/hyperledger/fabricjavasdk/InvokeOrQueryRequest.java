package org.hyperledger.fabricjavasdk;

import java.util.ArrayList;

/**
 * Invoke or query request.
 */
public class InvokeOrQueryRequest extends TransactionRequest {
    // Optionally pass a list of attributes which can be used by chaincode to perform access control
    ArrayList<String> attrs;
}
