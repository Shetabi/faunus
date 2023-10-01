# Getting Started with Create React App

This project was bootstrapped with [Create React App](https://github.com/facebook/create-react-app).

## Available Scripts

In the project directory, you can run:

### `npm start`

Runs the app in the development mode.\
Open [http://localhost:3000](http://localhost:3000) to view it in your browser.

The page will reload when you make changes.\
You may also see any lint errors in the console.

### `npm test`

Launches the test runner in the interactive watch mode.\
See the section about [running tests](https://facebook.github.io/create-react-app/docs/running-tests) for more information.

### `npm run build`

Builds the app for production to the `build` folder.\
It correctly bundles React in production mode and optimizes the build for the best performance.

The build is minified and the filenames include the hashes.\
Your app is ready to be deployed!

See the section about [deployment](https://facebook.github.io/create-react-app/docs/deployment) for more information.

### `npm run eject`

**Note: this is a one-way operation. Once you `eject`, you can't go back!**

If you aren't satisfied with the build tool and configuration choices, you can `eject` at any time. This command will remove the single build dependency from your project.

Instead, it will copy all the configuration files and the transitive dependencies (webpack, Babel, ESLint, etc) right into your project so you have full control over them. All of the commands except `eject` will still work, but they will point to the copied scripts so you can tweak them. At this point you're on your own.

You don't have to ever use `eject`. The curated feature set is suitable for small and middle deployments, and you shouldn't feel obligated to use this feature. However we understand that this tool wouldn't be useful if you couldn't customize it when you are ready for it.

## Learn More

You can learn more in the [Create React App documentation](https://facebook.github.io/create-react-app/docs/getting-started).

To learn React, check out the [React documentation](https://reactjs.org/).

### Code Splitting

This section has moved here: [https://facebook.github.io/create-react-app/docs/code-splitting](https://facebook.github.io/create-react-app/docs/code-splitting)

### Analyzing the Bundle Size

This section has moved here: [https://facebook.github.io/create-react-app/docs/analyzing-the-bundle-size](https://facebook.github.io/create-react-app/docs/analyzing-the-bundle-size)

### Making a Progressive Web App

This section has moved here: [https://facebook.github.io/create-react-app/docs/making-a-progressive-web-app](https://facebook.github.io/create-react-app/docs/making-a-progressive-web-app)

### Advanced Configuration

This section has moved here: [https://facebook.github.io/create-react-app/docs/advanced-configuration](https://facebook.github.io/create-react-app/docs/advanced-configuration)

### Deployment

This section has moved here: [https://facebook.github.io/create-react-app/docs/deployment](https://facebook.github.io/create-react-app/docs/deployment)

### `npm run build` fails to minify

This section has moved here: [https://facebook.github.io/create-react-app/docs/troubleshooting#npm-run-build-fails-to-minify](https://facebook.github.io/create-react-app/docs/troubleshooting#npm-run-build-fails-to-minify)


## Package structure

```bash
/src
  /components
    /Common
      - Header.js
      - Footer.js
    /Auth
      - LoginForm.js
      - RegistrationForm.js
    /Dashboard
      - Dashboard.js
      - Widget.js
  /containers
    /Public
      - Home.js
      - About.js
    /Private
      - DashboardPage.js
      - ProfilePage.js
  /context
    - AuthContext.js
  /hooks
    - useFetch.js
  /services
    - authService.js
    - apiService.js
  /utils
    - helpers.js
  /styles
    - main.css
  /assets
    /images
    /fonts
  /routes
    - AppRouter.js
  /redux
    /actions
    /reducers
    /store.js
  /config
    - config.js
  /tests
  - index.js
  - App.js
  - index.css
  - setupTests.js

```

Here's a breakdown of each directory in this suggested structure:

- components: This is where you store your reusable UI components. You can further organize them by creating subdirectories for common components and components related to specific parts of your application.

- containers: Containers are higher-level components that connect to your Redux store or manage state. Organize them based on whether they are meant for public or private routes.

- context: If you're using the Context API for state management, place your context providers and consumers here.

- hooks: Custom React hooks that you create for managing state, side effects, or other functionality.

- services: Place any services responsible for handling API requests, authentication, or other external interactions.

- utils: Utility functions and helper files that don't fit neatly into components or services.

- styles: CSS or other styling files for your application.

- assets: Store static assets like images and fonts here.

- routes: Define your application's routing configuration here. You might have a single AppRouter.js file that manages routes using a library like react-router.

- redux: If you're using Redux for state management, organize your actions, reducers, and the store configuration here.

- config: Configuration files for your application, such as environment-specific settings.

- tests: Place your unit tests and integration tests here, organized by component or feature.

- index.js: The entry point of your application, where you typically render the root component and set up Redux or any other context providers.

- App.js: The main component where you define the structure of your application, including routing.

- index.css: Global styles for your application.

- setupTests.js: Configuration for test setup.
