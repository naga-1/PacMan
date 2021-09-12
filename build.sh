rm -r ./class
mkdir ./class
javac -d class/ ./src/data/*.java ./src/logic/*.java ./src/view/*.java
cd class
cp ../MANIFEST.MF .
cp ../assets/levels.txt .
cp ../assets/highestScore.txt .
cp ../assets/gh.png .
cp ../assets/pc.png .
jar cvmf MANIFEST.MF PacMan.jar .
cd ..
rm PacMan.jar
mv ./class/PacMan.jar .
chmod +x PacMan.jar
java -jar PacMan.jar






