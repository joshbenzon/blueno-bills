import React from 'react';
import Button from '../../buttons/components/buttons'
import {BrowserRouter as Router, Routes, Route} from 'react-router-dom';

const Request = () => {
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

                <h1>What Type of Payment to Request?</h1>
            </div>

            <div className="request-meal-button"
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
                    onClick={() => console.log("Meal Credits!")}
                />
            </div>

            <div className="request-flex-button"
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
                    onClick={() => console.log("Flex Points!")}
                />
            </div>

            <div className="request-bear-button"
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
                    onClick={() => console.log("Bear Bucks!")}
                />
            </div>

        </div>
    );
};

export default Request;
