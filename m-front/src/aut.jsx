import React, { useState } from 'react';
import './aut.css'; // Подключаем CSS
import { Link, useNavigate } from 'react-router-dom'; // Импортируем useNavigate
import axios from 'axios'; // Импортируем axios

function AutPage() {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState(''); // Состояние для хранения ошибок

    const navigate = useNavigate(); // Получаем функцию для перехода

    const handleSubmit = async (e) => {
        e.preventDefault();
        setError(''); // Сбрасываем ошибку перед отправкой

        try {
            // Отправляем GET-запрос
            const response = await axios.get('http://localhost:8080/api/customers');

            // Проверяем, есть ли пользователь с такими данными
            const customers = response.data; // Получаем массив клиентов из ответа
            const user = customers.find(customer => customer.email === email && customer.password === password);

            if (user) {
                // Переходим на страницу /start с id клиента
                navigate(`/start/${user.customer_id}`); // Замените customer_id на актуальное поле, если нужно
            } else {
                setError('Неверные учетные данные. Попробуйте еще раз.'); // Устанавливаем ошибку
            }
        } catch (error) {
            console.error('Ошибка при авторизации:', error);
            setError('Ошибка при авторизации. Попробуйте позже.'); // Устанавливаем ошибку
        }
    };

    return (
        <div className="auth-body">
            <div className="auth-container">
                <h1 className="auth-container h1">Авторизация</h1>
                <form onSubmit={handleSubmit} className="auth-form">
                    <div className="auth-form-group">
                        <label htmlFor="email" className="auth-form-group label">Электронная почта</label>
                        <input 
                            type="email" 
                            id="email" 
                            value={email} 
                            onChange={(e) => setEmail(e.target.value)} 
                            required 
                            className="auth-form-group input" 
                        />
                    </div>
                    <div className="auth-form-group"> 
                        <label htmlFor="password" className="auth-form-group label">Пароль</label>
                        <input 
                            type="password" 
                            id="password" 
                            value={password} 
                            onChange={(e) => setPassword(e.target.value)} 
                            required 
                            className="auth-form-group input" 
                        />
                    </div>
                    {error && <p className="auth-error">{error}</p>} {/* Отображаем ошибку */}
                    <button type="submit" className="auth-button">Войти</button>
                    <div className="auth-footer">
                        <p>Нет аккаунта? <a href="/register">Зарегистрироваться</a></p>
                    </div>
                </form>
            </div>
        </div>
    );
}

export default AutPage;