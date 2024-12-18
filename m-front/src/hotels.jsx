import React, { useState, useEffect } from 'react';
import './hotels.css';
import { useNavigate, useParams } from 'react-router-dom';

function HotelsPage() {
    const navigate = useNavigate();
    const [currentIndex, setCurrentIndex] = useState(0);
    const [hotels, setHotels] = useState([]);
    const [roomDetails, setRoomDetails] = useState(null); // State for room details
    const [checkInDate, setCheckInDate] = useState(''); // State for check-in date
    const [checkOutDate, setCheckOutDate] = useState(''); // State for check-out date
    const { id } = useParams(); // Получаем id из параметров маршрута

    useEffect(() => {
        const fetchHotels = async () => {
            try {
                const response = await fetch('http://localhost:8080/api/hotels');
                const data = await response.json();
                setHotels(data); // Expecting data to be an array of hotel objects
            } catch (error) {
                console.error('Error fetching hotels:', error);
            }
        };

        fetchHotels();
    }, []);

    const handleNext = () => {
        setCurrentIndex((currentIndex + 1) % hotels.length);
        setRoomDetails(null); // Reset room details when changing hotel
    };

    const handlePrev = () => {
        setCurrentIndex((currentIndex - 1 + hotels.length) % hotels.length);
        setRoomDetails(null); // Reset room details when changing hotel
    };

    const handleBook = async () => {
        const bookingData = {
            booking_id: 1, // This could be dynamically generated or returned from the server
            customer_id: 2, // This should be the actual customer ID
            room_id: hotels[currentIndex].hotel_id, // Assuming hotel_id corresponds to room_id
            checkInDate: checkInDate, // Используем дату из input
            checkOutDate: checkOutDate // Используем дату выезда из input
        };

        try {
            const response = await fetch('http://localhost:8080/api/bookings', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(bookingData),
            });

            if (response.ok) {
                alert("Номер успешно забронирован!");
            } else {
                const errorData = await response.json();
                alert("Ошибка при бронировании: " + errorData.message);
            }
        } catch (error) {
            console.error('Error booking hotel:', error);
            alert("Произошла ошибка при бронировании номера.");
        }
    };

    const handleShowRoom = async () => {
        const roomId = hotels[currentIndex].hotel_id; // Assuming hotel_id corresponds to room_id
        if (roomDetails) {
            setRoomDetails(null); // Закрыть карточку, если она уже открыта
            return;
        }
        try {
            const response = await fetch(`http://localhost:8080/api/rooms/${roomId}`);
            if (response.ok) {
                const roomData = await response.json();
                setRoomDetails(roomData); // Set room details in state
            } else {
                const errorData = await response.json();
                alert("Ошибка при получении информации о комнате: " + errorData.message);
            }
        } catch (error) {
            console.error('Error fetching room details:', error);
            alert("Произошла ошибка при получении информации о комнате.");
        }
    };

    return (
        <div>
            <div className="hotels-container">
                {hotels.length > 0 ? (
                    <>
                        <button className="nav-button prev-button" onClick={handlePrev}>&#8249;</button>
                        <div className="hotel-card">
                            <div className="hotel-info">
                                <h2>{hotels[currentIndex].name}</h2>
                                <p>{hotels[currentIndex].address}</p>
                                <p>{hotels[currentIndex].phone}</p>
                                
                                <p>Дата заезда</p>
                                <input 
                                    type="date" 
                                    value={checkInDate}
                                    onChange={(e) => setCheckInDate(e.target.value)} 
                                />
                                
                                <p>Дата выезда</p>
                                <input 
                                    type="date" 
                                    value={checkOutDate}
                                    onChange={(e) => setCheckOutDate(e.target.value)} 
                                />
                                
                                <button className="book-button" onClick={handleBook}>Забронировать</button>
                                <button className="book-button" onClick={handleShowRoom}>Показать номер</button>
                            </div>
                            <img src={hotels[currentIndex].imageh} alt={hotels[currentIndex].name} className="hotel-image" />
                        </div>

                        <button className="nav-button next-button" onClick={handleNext}>&#8250;</button>
                    </>
                ) : (
                    <p>Загрузка отелей...</p>
                )}
                <button className="nav-button" onClick={() => navigate(`/start/${id}`)}>Назад</button>
            </div>

            {/* Данные о комнате отображаются ниже информации об отеле */}
            {roomDetails && (
                <div className="hotels-container">
                    <div className="hotel-card">
                        <div className="hotel-info">
                            <h3>Детали комнаты</h3>
                            <p><strong>Номер:</strong> {roomDetails.roomNumber}</p>
                            <p><strong>Тип:</strong> {roomDetails.roomType}</p>
                            <p><strong>Цена:</strong> {roomDetails.price} руб.</p>
                        </div>
                        <img src={roomDetails.imager} alt={roomDetails.roomType} className="room-image" />
                    </div>
                </div>
            )}
        </div>
    );
}

export default HotelsPage;