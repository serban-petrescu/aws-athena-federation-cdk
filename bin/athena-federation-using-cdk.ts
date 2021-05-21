#!/usr/bin/env node
import 'source-map-support/register';
import * as cdk from '@aws-cdk/core';
import { PocCdkStack } from '../lib/poc-stack';

const app = new cdk.App();
new PocCdkStack(app, 'P1PocAthenaFederationUsingCdkStack');
