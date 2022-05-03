import React from 'react';
import Button from '../../buttons/components/buttons'

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
                    onClick={() => window.location.href='/requestMeal'}
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
                    onClick={() => window.location.href='/requestFlex'}
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
                    onClick={() => window.location.href='/requestBear'}
                />
            </div>

        </div>
    );
};

export default Request;
