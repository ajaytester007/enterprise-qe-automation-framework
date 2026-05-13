pipeline {
  agent any
  tools { jdk 'JDK17'; maven 'Maven3' }
  stages {
    stage('Checkout') { steps { checkout scm } }
    stage('Smoke Quality Gate') { steps { sh 'mvn clean test -Psmoke -Dheadless=true' } }
    stage('API + DB Validation') { steps { sh 'mvn test -Papi-db -Dheadless=true' } }
    stage('Parallel Regression') { steps { sh 'mvn test -DsuiteXmlFile=testng-parallel.xml -Dheadless=true' } }
  }
  post {
    always { junit 'target/surefire-reports/*.xml'; archiveArtifacts artifacts: 'target/**', allowEmptyArchive: true }
  }
}
