module.exports = {
    devServer: {
      port: 8081,
      proxy: {
        "/api/" : {
          target: "http://localhost:8080/",
          logLevel: "debug"
        }
      }
    },
/*
    publicPath: process.env.NODE_ENV === 'production'
    ? '/kSmart-web/'
    : '/'
 */   
  }