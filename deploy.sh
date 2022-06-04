SOURCE_DIR="/mnt/c/CSCI5308"
DEPLOY_DIR="/mnt/c/CSCI5308"
SOURCE_REPO="group-2"
SOURCE_BRANCH="development"
DEPLOY_REPO="java-getting-started"

cd $SOURCE_DIR/$SOURCE_REPO
git pull origin $SOURCE_BRANCH
cd $DEPLOY_DIR/$DEPLOY_REPO
cp -r $SOURCE_DIR/$SOURCE_REPO/* $DEPLOY_DIR/$DEPLOY_REPO

git add .
git commit -m "pushing new build"
git push heroku main