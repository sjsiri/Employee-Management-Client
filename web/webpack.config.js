const path = require('path');
const CopyPlugin = require("copy-webpack-plugin");
const webpack = require('webpack');

// if we have an API resource id set as an env variable we will use that as the
// baseUrl, otherwise we will default to localhost
const apiResourceId = process.env.U5_API_RESOURCE_ID;
const baseUrl = apiResourceId ? `https://${apiResourceId}.execute-api.us-east-2.amazonaws.com/Prod` : "http://localhost:3000";

module.exports = {
  plugins: [
    new CopyPlugin({
      patterns: [
        {
          from: "static_assets", to: "../",
          globOptions: {
            ignore: ["**/.DS_Store"],
          },
        },
      ],
    }),
    new webpack.DefinePlugin({
        INVOKE_URL : JSON.stringify(baseUrl)
    })
  ],
  optimization: {
    usedExports: true
  },
  entry: {
  viewEmployees: path.resolve(__dirname, 'src', 'pages', 'viewEmployees.js'),
  createEmployee: path.resolve(__dirname, 'src', 'pages', 'createEmployee.js'),
  viewEmployeeDetail: path.resolve(__dirname, 'src', 'pages', 'viewEmployeeDetail.js'),
  updateEmployee: path.resolve(__dirname, 'src', 'pages', 'updateEmployee.js'),
  updateDepartment: path.resolve(__dirname, 'src', 'pages', 'updateDepartment.js'),
  viewDepartments: path.resolve(__dirname, 'src', 'pages', 'viewDepartments.js'),
  viewDepartmentDetail: path.resolve(__dirname, 'src', 'pages', 'viewDepartmentDetail.js'),
  createDepartment: path.resolve(__dirname, 'src', 'pages', 'createDepartment.js'),
  },
  output: {
    path: path.resolve(__dirname, 'build', 'assets'),
    filename: '[name].js',
    publicPath: '/assets/'
  },
  devServer: {
    static: {
      directory: path.join(__dirname, 'static_assets'),
    },
    port: 8000,
    client: {
      // overlay shows a full-screen overlay in the browser when there are js compiler errors or warnings
      overlay: true,
    },
  }
}
