
// A request to get a batch of TCerts
export class GetTCertBatchRequest {
   constructor( public name: string,
                public enrollment: Enrollment,
                public num: number,
                public attrs: string[]) {};
}
