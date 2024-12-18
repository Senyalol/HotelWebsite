import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import './aut.css';

function RegPage() {
  const navigate = useNavigate();
  const [formData, setFormData] = useState({
    firstName: '',
    lastName: '',
    email: '',
    phone: '',
    password: '',
  });

  const handleChange = (e) => {
    const { id, value } = e.target;
    setFormData((prevData) => ({
      ...prevData,
      [id]: value,
    }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    const requestData = {
      
      firstName: formData.firstName,
      lastName: formData.lastName,
      email: formData.email,
      phone: formData.phone,
      password: formData.password,
    };

    try {
      const response = await fetch('http://localhost:8080/api/customers', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(requestData),
      });

      if (!response.ok) {
        throw new Error('Ошибка при регистрации');
      }

      // Если регистрация успешна, перенаправим пользователя
      navigate('/');
    } catch (error) {
      console.error(error);
      alert('Произошла ошибка при регистрации. Пожалуйста, попробуйте еще раз.');
    }
  };

  return (
    <div className="auth-body">
      <div className="auth-container">
        <h1>Регистрация</h1>
        <form onSubmit={handleSubmit}>
          <div className="auth-form-group">
            <label htmlFor="firstName">Имя</label>
            <input type="text" id="firstName" required onChange={handleChange} />
          </div>
          <div className="auth-form-group">
            <label htmlFor="lastName">Фамилия</label>
            <input type="text" id="lastName" required onChange={handleChange} />
          </div>
          <div className="auth-form-group">
            <label htmlFor="email">Адрес эл. почты</label>
            <input type="email" id="email" required onChange={handleChange} />
          </div>
          <div className="auth-form-group">
            <label htmlFor="phone">Контактный телефон</label>
            <input type="text" id="phone" required onChange={handleChange} />
          </div>
          <div className="auth-form-group">
            <label htmlFor="password">Пароль</label>
            <input type="password" id="password" required onChange={handleChange} />
          </div>
          <button type="submit" className="auth-button">Зарегистрироваться</button>
        </form>
      </div>
    </div>
  );
}

export default RegPage;