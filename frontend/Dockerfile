FROM node:18-alpine

WORKDIR /frontend

# copy the json file first
COPY ./package.json /frontend

# install npm dependencies
RUN npm install --verbose

# copy other project files
COPY . .

# build the folder
RUN npm run build

CMD [ "npm", "run", "start" ]

