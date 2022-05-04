import React from "react";

const Home = () => {
// websockets
    return (
        <div
            style={{
                display: 'flex',
                justifyContent: 'Center',
                alignItems: 'Top',
                height: '100vh'
            }}
        >
            <h1>Home Page!</h1>
            <body>
                <ul id="userlist">  </ul>
                <div id="chat">  </div>
                <script src="websocketDemo.js"></script>
            </body>
        </div>
    );

  // pure frontend
//   return (
//     <React.Fragment>
//       <div className={"page-info"}>
//         <h1>Home Page!</h1>
//       </div>
//     </React.Fragment>
//   );
};

export default Home;
