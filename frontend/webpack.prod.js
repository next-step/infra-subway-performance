const path = require('path')
const BundleAnalyzerPlugin = require('webpack-bundle-analyzer').BundleAnalyzerPlugin;

const outputPath = path.resolve(__dirname, '../src/main/resources/static')

module.exports = {
  mode: 'production',
  output: {
    path: outputPath,
    plugins : [
      new BundleAnalyzerPlugin()
    ],
    filename: '[name].js'
  }
}
