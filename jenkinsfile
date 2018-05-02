#!groovy

pipeline {
    options {
        buildDiscarder(
                logRotator(artifactDaysToKeepStr: '21', artifactNumToKeepStr: '4', daysToKeepStr: '21', numToKeepStr: '4')
        )
        gitLabConnection('GitLab')
    }

    agent any
    tools {
        maven 'Default Maven'
        jdk 'DefaultJDK'
    }

    stages {
        stage('Source Code Analysis') {
            steps {
                updateGitlabCommitStatus(name: 'jenkins-build', state: 'running')
                withMaven() {
                    sh "mvn -Dsonar.host.url=http://sonar.hermescloud.co.uk -Dsonar.branch='${env.BRANCH_NAME}' -Dsonar.login=599225e1d38812984221c10475449e647ba72efb sonar:sonar"
                }
            }
        }
    }


    post {
        success {
            echo 'posting success to GitLab'
            updateGitlabCommitStatus(name: 'jenkins-build', state: 'success')
        }
        failure {
            echo 'posting failure to GitLab'
            updateGitlabCommitStatus(name: 'jenkins-build', state: 'failed')
        }
        always {
            deleteDir()
        }
    }
}

@NonCPS
def getChangeString() {
    MAX_MSG_LEN = 100
    def changeString = ""

    def changeLogSets = currentBuild.changeSets
    for (int i = 0; i < changeLogSets.size(); i++) {
        def entries = changeLogSets[i].items
        for (int j = 0; j < entries.length; j++) {
            def entry = entries[j]
            truncated_msg = entry.msg.take(MAX_MSG_LEN)

            changeString += "Commit ${entry.commitId} by ${entry.author}\n ${truncated_msg} \n"

            def files = new ArrayList(entry.affectedFiles)

            for (int k = 0; k < files.size(); k++) {
                def file = files[k]
                def filePath = file.path.replaceAll("/src/test/java/uk/co/hermes/", "/../").replaceAll("/src/main/java/uk/co/hermes/", "/../")
                changeString += "  ${file.editType.name} ${filePath} \n"
            }
        }
    }

    if (!changeString) {
        changeString = " - No new changes"
    }
    return changeString
}