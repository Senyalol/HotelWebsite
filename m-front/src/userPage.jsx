import React, { useState, useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import './userPage.css';

function UserPage() {
    const [userData, setUserData] = useState(null); // Состояние для хранения данных пользователя
    const [bookings, setBookings] = useState([]); // Состояние для хранения бронирований
    const [loading, setLoading] = useState(true); // Состояние для индикатора загрузки
    const [error, setError] = useState(null); // Состояние для хранения ошибок
    const { id } = useParams(); // Извлекаем id из параметров маршрута

    useEffect(() => {
        const fetchUserData = async () => {
            try {
                const response = await fetch(`http://localhost:8080/api/customers/${id}`);
                if (!response.ok) {
                    throw new Error('Ошибка при загрузке данных пользователя');
                }
                const data = await response.json();
                setUserData(data); // Устанавливаем данные пользователя
            } catch (err) {
                setError(err.message); // Устанавливаем сообщение об ошибке
            }
        };

        const fetchUserBookings = async () => {
            try {
                const response = await fetch(`http://localhost:8080/api/bookings/customer/${id}`);
                if (!response.ok) {
                    throw new Error('Ошибка при загрузке бронирований');
                }
                const data = await response.json();
                setBookings(data); // Устанавливаем данные бронирований
            } catch (err) {
                setError(err.message); // Устанавливаем сообщение об ошибке
            } finally {
                setLoading(false); // Завершаем загрузку
            }
        };

        // Запускаем запросы
        fetchUserData();
        fetchUserBookings();
    }, [id]); // Добавлено id в зависимости для useEffect

    // Если данные загружаются, показываем индикатор загрузки
    if (loading) {
        return <div>Загрузка...</div>;
    }

    // Если произошла ошибка, показываем сообщение об ошибке
    if (error) {
        return <div>Ошибка: {error}</div>;
    }

    return (
        <div className="info-container">
            <div className="info-content">
                <h1>Добро пожаловать, {userData.firstName} {userData.lastName}</h1>
                <h2>Email: {userData.email}</h2>
                <h2>Ваш контактный телефон: {userData.phone}</h2>
                <h3>Ваши бронирования:</h3>
                
                <div className="bookings">
                    {bookings.length > 0 ? (
                        bookings.map((booking, index) => (
                            <div key={booking.booking_id} className="booking-item">
                                <p>Бронирование номер: {index + 1}</p> {/* Инкрементный номер */}
                                <p>Дата заезда: {booking.checkInDate}</p>
                                <p>Дата выезда: {booking.checkOutDate}</p>
                            </div>
                        ))
                    ) : (
                        <p>У вас нет активных бронирований.</p>
                    )}
                </div>
                
                <Link to={`/start/${id}`} className="link">Назад</Link>
                <Link to="/" className="link">К авторизации</Link>
            </div>
        </div>
    );
}

export default UserPage;