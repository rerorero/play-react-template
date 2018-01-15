#!/bin/bash

set -eu

git clean -fdx

(
  cd front
  npm install
  export CI=true
  npm run test
)

sbt compileAll test:compile test dist
