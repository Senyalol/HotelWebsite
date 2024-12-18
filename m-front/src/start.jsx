import React, { useState, useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import './start.css';

function StartPage() {
    const [currentTime, setCurrentTime] = useState(new Date());
    const { id } = useParams(); // Получаем id из параметров маршрута

    useEffect(() => {
        const intervalId = setInterval(() => {
            setCurrentTime(new Date());
        }, 1000);

        return () => clearInterval(intervalId);
    }, []);

    const formattedTime = currentTime.toLocaleString();

    return (
        <div className="start-container">
            <div className="start-date-time">
                <p>{formattedTime}</p>
            </div>
            <h1 className="start-container h1">Добро пожаловать</h1>
            <div className="start-button-group">
                <Link to={`/hotels/${id}`}>
                    <button data-tooltip="Список отелей">Отели</button>
                </Link>
                <Link to={`/bookings/${id}`}> {/* Передаем id в URL */}
                    <button data-tooltip="Данные о бронях и регистрационные данные">Личный кабинет</button>
                </Link>
                <Link to={`/info/${id}`}>
                    <button data-tooltip="Информация о компании">О нас</button>
                </Link>
            </div>
        </div>
    );
}

export default StartPage;