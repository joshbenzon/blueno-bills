import React from 'react';

const Home = () => {
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
};

export default Home;
