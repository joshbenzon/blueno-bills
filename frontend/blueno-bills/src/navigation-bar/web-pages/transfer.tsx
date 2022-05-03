import React from 'react';

const Transfer = () => {
    return (
        <div className={"web-page"}>

            <div className="transfer-info">
                <h1>What Type of Payment to Transfer?</h1>
            </div>

            <div className="transfer-meal-button">
                <button onClick={() => window.location.href = '/transferMeal'}>
                    Meal Credits
                </button>
            </div>

            <div className="transfer-flex-button">
                <button onClick={() => window.location.href = '/transferFlex'}>
                    Flex Points
                </button>
            </div>

            <div className="transfer-bear-button">
                <button onClick={() => window.location.href = '/transferBear'}>
                    Bear Bucks
                </button>
            </div>

        </div>
    );
};

export default Transfer;
