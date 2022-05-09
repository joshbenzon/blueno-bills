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
}
const RequestMeal = (props: TableProp) => {
    return (
        <div
            style={{
                display: 'flex',
                justifyContent: 'Center',
                alignItems: 'Top',
                height: '100vh'
            }}
        >
            <h1>Request Meal Page!</h1>
        </div>
    );
};

export default RequestMeal;
