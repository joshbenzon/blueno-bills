import React from 'react';

const Request = () => {
    return (
        <div className={"web-page"}>

            <div className="request-info">
                <h1>What Type of Payment to Request?</h1>
            </div>

            <div className="request-meal-button">
                <button onClick={() => window.location.href = '/requestMeal'}>
                    Meal Credits
                </button>
            </div>

            <div className="request-flex-button">
                <button onClick={() => window.location.href = '/requestFlex'}>
                    Flex Points
                </button>
            </div>

            <div className="request-bear-button">
                <button onClick={() => window.location.href = '/requestBear'}>
                    Bear Bucks
                </button>
            </div>

        </div>
    );
};

export default Request;
