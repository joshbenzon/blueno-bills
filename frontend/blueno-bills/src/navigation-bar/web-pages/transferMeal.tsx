import React from 'react';
import {useForm} from "react-hook-form";

import axios from 'axios';
import { useState } from 'react';

interface StudentData {
    StudentID: string;
    email: string;
    mealSwipes: string;
    bearBucks: string;
    flexPoints: string;
}

function setStudentData(studentData: StudentData): void {
    console.log(studentData["StudentID"])
}

const TableRequest = async () => {
    // const request = 'http://localhost:4567/table';  // 1) location for request

    // let config = {  // 3) configuration
    //     headers: {
    //         "Content-Type": "application/json",
    //         'Access-Control-Allow-Origin': '*',
    //     }
    // }

    // axios.get(request, config)

    //     .then((response: any) => {
    //         console.log(response.data);
    //     })

    //     .catch((error: any) => {
    //         console.log(error);
    //     });
    console.log("enters table request")
    const response = await fetch("http://localhost:4567/table", {
        method: 'GET',
    })
    console.log("response: " + response.status)
    
}

const UpdateRequest = () => {
    const request = 'http://localhost:4567/update';  // 1) location for request

    let config = {  // 3) configuration
        headers: {
            "Content-Type": "application/json",
            'Access-Control-Allow-Origin': '*',
        }
    }

    axios.post(request, config)

        .then((response: any) => {
            console.log(response.data);
        })

        .catch((error: any) => {
            console.log(error);
        });
}

const TransferMeal = () => {
    const {register, handleSubmit, formState: {errors}} = useForm();
    // const onSubmit = (data: any) => UpdateRequest;  // stores in map

    return (
        <div className={"web-page"}>

            <div className="transfer-meal-info">
                <h1>Transfer: Meal Credits</h1>
            </div>

            <form onSubmit={handleSubmit(TableRequest)}>

                <div>
                    To Who:
                    <input type="text" {...register("name",
                        {required: true, maxLength: 20, pattern: /^[A-Za-z]+$/i})} />
                    {errors.name && "Required!"}
                </div>

                <div>
                    Description:
                    <input type="text" {...register("description", {maxLength: 100})} />
                </div>

                <div>
                    Amount:
                    <input type="number" {...register("amount", {required: true, min: 0, max: 20})} />
                    {errors.amount && "Required and Must Be in Bounds!"}
                </div>

                <div>
                    <button className="sending-button" type="submit">
                        Submit
                    </button>
                </div>

            </form>

        </div>
    );
};

export default TransferMeal;
