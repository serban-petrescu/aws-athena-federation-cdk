#!/usr/bin/env node
import 'source-map-support/register';
import * as cdk from '@aws-cdk/core';
import { AthenaFederationUsingCdkStack } from '../lib/athena-federation-using-cdk-stack';

const app = new cdk.App();
new AthenaFederationUsingCdkStack(app, 'AthenaFederationUsingCdkStack');
