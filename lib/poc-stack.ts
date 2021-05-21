
import { join } from 'path';
import * as cdk from '@aws-cdk/core';
import * as lambda from '@aws-cdk/aws-lambda';
import * as athena from '@aws-cdk/aws-athena';

export class PocCdkStack extends cdk.Stack {
  constructor(scope: cdk.Construct, id: string, props?: cdk.StackProps) {
    super(scope, id, props);

    const func = new lambda.Function(this, 'data-source', {
      runtime: lambda.Runtime.JAVA_11,
      code: lambda.Code.fromAsset(join(__dirname, 'data-source', 'target', 'athena-example-1.0.jar')), // you need to run maven package first
      handler: "com.devfactory.p1.pocs.athena.PoCCompositeHandler",
      timeout: cdk.Duration.minutes(1),
      memorySize: 512
    });

    const catalog = new athena.CfnDataCatalog(this, 'data-catalog', {
      name: 'p1poc',
      type: 'LAMBDA',
      parameters: {
        "function": func.functionArn
      }
    });


    new cdk.CfnOutput(this, 'catalog', {
      value: catalog.name
    });
  }
}
