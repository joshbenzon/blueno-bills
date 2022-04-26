import React from 'react';
import Button from "../../buttons/components/buttons";

const Transfer = () => {
    return (
        <div
            style={{
                display: 'flex',
                justifyContent: 'Center',
                alignItems: 'Top',
                height: '100vh',
                padding: '10px'
            }}>

            <div className="request-info"
                 style={{
                     display: 'flex',
                     padding: '10px'
                 }}>

                <h1>What Type of Payment to Transfer?</h1>
            </div>

            <div className="transfer-meal-button"
                 style={{
                     display: 'flex',
                     padding: '10px'
                 }}>

                <Button
                    border="none"
                    color="#79ACFF"
                    height="100px"
                    width="200px"
                    radius="5%"
                    children="Meal Credits"
                    onClick={() => window.location.href='/transferMeal'}
                />
            </div>

            <div className="transfer-flex-button"
                 style={{
                     display: 'flex',
                     padding: '10px'
                 }}>

                <Button
                    border="none"
                    color="#79ACFF"
                    height="100px"
                    width="200px"
                    radius="5%"
                    children="Flex Points"
                    onClick={() => window.location.href='/transferFlex'}
                />
            </div>

            <div className="transfer-bear-button"
                 style={{
                     display: 'flex',
                     padding: '10px'
                 }}>

                <Button
                    border="none"
                    color="#79ACFF"
                    height="100px"
                    width="200px"
                    radius="5%"
                    children="Bear Bucks"
                    onClick={() => window.location.href='/transferBear'}
                />
            </div>

        </div>
    );
};

export default Transfer;
