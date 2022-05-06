import React from "react";
import {useForm} from "react-hook-form";

import axios from 'axios';

<<<<<<< HEAD
// inputs as props in console.log

interface TransferMealProps {
    tableName: string | null;
    tableHeaders: string[] | null;
    rows: string[][] | null;
}

interface InputProps {
    inputName: string | null;
    inputDescription: string | null;
    inputAmount: number | null;
}

function TransferMeal(props: TransferMealProps) {
    const [inputName, setInputName] = useState<string | null>(null);
    const [inputDescription, setInputDescription] = useState<string | null>(null);
    const [inputAmount, setInputAmount] = useState<number | null>(null);
=======
import { useState } from 'react';


interface Row{
    StudentID: string;
    email:string;
    mealSwipes:string;
    flexPoints:string;
    bearBucks:string;
}

interface TransferMealProp{
    tableHeaders: string[]|null;
    rows: Row[]|null;
}
>>>>>>> c30b94d36fabac056f3270980f7b0d085a07fe50

function TransferMeal(props:TransferMealProp){
    const {register, handleSubmit, formState: {errors}} = useForm();
    // const onSubmit = (data: any) => UpdateRequest;  // stores in map



<<<<<<< HEAD
    const updateInputs = (event: any) => {
        setInputName(event.target)
        console.log(event.target)
    };

    const UpdateRequest = () => {  // makes post request
        console.log("name: " + props.tableName)
        console.log("headers: " + props.tableHeaders);
        console.log("rows: " + props.rows);

=======
    const UpdateRequest = () => {
        console.log("headers: " + props.tableHeaders);
        console.log("rows: " + props.rows);
>>>>>>> c30b94d36fabac056f3270980f7b0d085a07fe50
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

    return (
        <React.Fragment>
            <div className="page-info">
                <h1>Transfer: Meal Credits</h1>
            </div>

            <div className={"page-buttons"}>
                <form onSubmit={handleSubmit(onSubmit)}>

                    <div className="input-group">
                        <div>To Who*:</div>

                        <input
                            type="text"
                            placeholder="Name"
                            {...register("name", {
                                required: true,
                                maxLength: 20,
                                pattern: /^[A-Za-z]+$/i,
                            })}
                        />
                        <div className="error-message">{errors.name && "Required!"}</div>
                    </div>

                    <div className="input-group">
                        <div>Description:</div>

                        <input
                            type="text"
                            placeholder="Optional"
                            {...register("description", {maxLength: 100})}
                        />
                    </div>

                    <div className="input-group">
                        <div>Amount*:</div>

                        <input
                            type="number"
                            placeholder="Between 1-20"
                            {...register("amount", {required: true, min: 1, max: 20})}
                        />
                        <div className="error-message">{errors.amount && "Required!"}</div>
                    </div>

                    <div className="input-group">
                        <button className="sending-button" type="submit">
                            Transfer!
                        </button>
                    </div>
                </form>
            </div>

<<<<<<< HEAD
=======



>>>>>>> c30b94d36fabac056f3270980f7b0d085a07fe50
        </React.Fragment>
    );
}

export default TransferMeal;
