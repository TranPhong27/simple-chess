# Simple Chess

Java desktop chess application (2-player) built with Swing/AWT.

Build & run (from project root):

```bash
javac -d out -sourcepath src src/main/Main.java
java -cp out main.Main
```

Suggested .gitignore already added.

How to push to GitHub (quick):

1. Initialize repo & commit:

```bash
cd "d:\Java Project\Simple Chess"
git init
git add .
git commit -m "Initial commit: Simple Chess"
```

2a. Using GitHub CLI (`gh`):

```bash
gh repo create your-username/simple-chess --public --source=. --remote=origin --push
```

2b. Or create a new repo on GitHub web, then:

```bash
git remote add origin https://github.com/your-username/simple-chess.git
git branch -M main
git push -u origin main
```

Authentication: configure `gh auth login` or set up Git credentials/SSH before pushing.

If you want, I can run the git commands here or create the remote for you. Replace `your-username` with your GitHub username.
