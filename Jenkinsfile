pipeline {
  agent any
  stages {
    
   stage ('Test') { // la phase build
steps {
bat 'gradlew test'
 junit 'build/test-results/test/TEST-Matrix.xml'
  
   cucumber buildStatus: 'UNSTABLE',
                reportTitle: 'My report',
                fileIncludePattern: 'target/report.json',
     
                trendsLimit: 10

}
}
   
    
        stage('Code Analysis') {
          steps {
            withSonarQubeEnv('sonar') {
              bat 'gradle sonar'
            }


          }
        }
    
    
         stage("Code Quality") {
            steps {
                timeout(time: 1, unit: 'HOURS') {
                    // Parameter indicates whether to set pipeline to UNSTABLE if Quality Gate fails
                    // true = set pipeline to UNSTABLE, false = don't
                    waitForQualityGate abortPipeline: true
                }
            }
        }
    
    
     stage('build') {
     
      /
       
       
       
       /*post {
        success {
          mail(subject: 'Build Success', body: 'New Build is deployed !', from: 'jm_gouasmia@esi.dz', to: 'jm_gouasmia@esi.dz')
        }
        failure {
          mail(subject: 'Build Failure', body: "the new build isn't deployed succesfully !", from: 'jm_gouasmia@esi.dz', to: 'm_gouasmia@esi.dz')
        }
        
      }
      */
      steps {
        bat(script: 'gradle build', label: 'gradle build')
        bat 'gradle javadoc'
        archiveArtifacts 'build/libs/*.jar'
        junit(testResults: 'build/reports/tests/test', allowEmptyResults: true)
        
      }
    }
   
     stage('Deploy') {
      steps {
        bat 'gradle publish'
      }
    }
    
     stage('Notification') {
      steps {
        
        slackSend(baseUrl: 'https://hooks.slack.com/services/', token: 'xoxe.xoxp-1-Mi0yLTQ2Mzk2NzU1NzcxNTgtNDY0ODc0MDc0MzA5Mi00NjM5NzE5MzcxOTQyLTQ2NzAwMTg2NDIzMDQtMDE1YjRkMjQ3Y2M1Nzk2YmU5ODFhODE4ZjI2OTY2MzVmNjNmMTQ2ZGY4NmMwYjE0YjRiY2E3YmYzZjlkNDQ5OA', message: 'New build is Created', channel: 'OGL')
      }
    }
    
    
    
    
    
   
}

 }

