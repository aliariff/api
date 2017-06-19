<?php

/*
|--------------------------------------------------------------------------
| Web Routes
|--------------------------------------------------------------------------
|
| Here is where you can register web routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| contains the "web" middleware group. Now create something great!
|
*/

Route::get('/', function () {
    return view('welcome');
});

Route::get('coments', 'ComentController@index');
Route::get('coments/{id}', 'ComentController@show');
Route::post('coments', 'ComentController@store')->middleware('auth.basic');
Route::put('coments/{id}', 'ComentController@update')->middleware('auth.basic');
Route::delete('coments/{id}', 'ComentController@destroy')->middleware('auth.basic');
