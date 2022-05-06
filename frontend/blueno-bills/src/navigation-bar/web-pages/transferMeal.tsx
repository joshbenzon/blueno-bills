import React from "react";
import {useForm} from "react-hook-form";

import axios from 'axios';
import {useState} from 'react';

// import { useTable } from 'react-table';

interface TransferMealProp {
    tableName: string | null;
    tableHeaders: string[] | null;
    rows: string[][] | null;
}

function TransferMeal(props: TransferMealProp) {
    const tableData: string[][] | null = props.rows;

    const {register, handleSubmit, formState: {errors}} = useForm();

    const onSubmit = (data: any) => console.log(data); // stores in map

    const UpdateRequest = () => {  // makes post request
        console.log("name: " + props.tableName)
        console.log("headers: " + props.tableHeaders);
        console.log("rows: " + props.rows);
        // console.log("rows length: " + props.rows?.length);
        // console.log("rows values: " + props.rows?.at(1));

        const request = 'http://localhost:4567/update';  // 1) location for request

        const toSend = {  // 2) your data
            "tableName": props.tableName,
            "tableHeaders": props.tableHeaders,
            "rows": props.rows
        }

        let config = {  // 3) configuration
            headers: {
                "Content-Type": "application/json",
                'Access-Control-Allow-Origin': '*',
            }
        }

        axios.post(request, toSend, config)

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
                <form onSubmit={handleSubmit(UpdateRequest)}>

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



        </React.Fragment>
    );
}

export default TransferMeal;
