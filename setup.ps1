param (
    [string] $action
)
$JAVA_HOME = "C:\Users\yxqsnz\Documents\jdks\11"
$SILYX_EXEC_PATH = "build/libs/SilyxKord-1.0-SNAPSHOT.jar"
function build_bot() {
    Write-Output "Criando FatJar do silyx... JAVA_HOME: $JAVA_HOME"
    $env:path = "$env:path;$JAVA_HOME\bin"
    $env:JAVA_HOME = $JAVA_HOME;
    .\gradlew build;
}
function run_bot() {
    build_bot;
    Write-Output "Executando bot com $ram MiB para o heap";

    $JAVA_ARGS =  "-Xmx512M -Xms512M -jar $SILYX_EXEC_PATH"
    .$JAVA_HOME\bin\java.exe $JAVA_ARGS

}

if ($action -eq "/b") {
    build_bot
} elseif ($action -eq "/r") {
    run_bot
}


