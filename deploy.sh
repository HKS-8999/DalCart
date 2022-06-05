SOURCE_DIR="/users/grad/tagarwal/CSCI5308builds"
DEPLOY_DIR="/users/grad/tagarwal/CSCI5308builds"
SOURCE_REPO="group-2"
SOURCE_BRANCH="development"
DEPLOY_REPO="dalcart-production"

cd $SOURCE_DIR/$SOURCE_REPO
git pull origin $SOURCE_BRANCH
cd $DEPLOY_DIR/$DEPLOY_REPO
cp -r $SOURCE_DIR/$SOURCE_REPO/* $DEPLOY_DIR/$DEPLOY_REPO

git add .
git commit -m "pushing new build"
git push heroku main