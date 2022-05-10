import React from 'react';

interface Row{
    StudentID: string;
    email:string;
    mealSwipes:string;
    flexPoints:string;
    bearBucks:string;
}

interface TableProp  {
    tableHeaders : string[] | null;
    rows : Row[] | null;
    userEmail :string;
    userBB: number;
}

function TransferBear(props: TableProp){

    return (
        <div
            style={{
                display: 'flex',
                justifyContent: 'Center',
                alignItems: 'Top',
                height: '100vh'
            }}
        >
            <h1>Transfer Bear Page!</h1>
        </div>
    );
};

export default TransferBear;
