GO
USE master
GO
CREATE DATABASE Car
GO
USE Car
GO
CREATE TABLE [dbo].[Role](
    [id] INT IDENTITY(1,1) PRIMARY KEY,
    [name] NVARCHAR(50) NOT NULL,
	[status] BIT DEFAULT 1
);
GO
CREATE TABLE [dbo].[User](
    [id] INT IDENTITY(1,1) PRIMARY KEY,
    [username] NVARCHAR(50) NOT NULL,
    [fullname] NVARCHAR(255) NOT NULL,
    [password] VARCHAR(50) NOT NULL,
	[address] NVARCHAR(255) NOT NULL,
    [phone] VARCHAR(11),
    [role_id] INT,
    [create_date] DATE,
    [update_date] DATE,
	[status] BIT DEFAULT 1,
    CONSTRAINT [FK_User_Role] FOREIGN KEY([role_id]) REFERENCES [dbo].[Role]([id])
);
GO
CREATE TABLE [dbo].[Category](
    [id] INT IDENTITY(1,1) PRIMARY KEY,
    [name] NVARCHAR(255) NOT NULL,
	[status] BIT DEFAULT 1,
);
GO
CREATE TABLE [dbo].[Product](
    [id] INT IDENTITY(1,1) PRIMARY KEY,
    [name] NVARCHAR(255) NOT NULL,
    [price] DOUBLE PRECISION NOT NULL,
    [title] NVARCHAR(255) NOT NULL,
	[color] NVARCHAR(255) NOT NULL,
	[image] NVARCHAR(MAX) NOT NULL,
	[stock] INT NOT NULL,
    [description] NVARCHAR(MAX),
    [create_date] DATE,
    [update_date] DATE,
    [category_id] INT,
	[status] BIT DEFAULT 1,
    CONSTRAINT [FK_Product_Category] FOREIGN KEY([category_id]) REFERENCES [dbo].[Category]([id])
);
GO
CREATE TABLE [dbo].[Order](
    [id] INT IDENTITY(1,1) PRIMARY KEY,
    [user_id] INT,
    [create_date] DATE,
    [update_date] DATE,
	[order_status] NVARCHAR(50) DEFAULT 'Pending',
	[status] BIT DEFAULT 1,
    CONSTRAINT [FK_Order_User] FOREIGN KEY([user_id]) REFERENCES [dbo].[User]([id])
);
GO
CREATE TABLE [dbo].[OrderDetail](
    [id] INT IDENTITY(1,1) PRIMARY KEY,
	[price] DOUBLE PRECISION NOT NULL, 
	[quantity] INT,
    [order_id] INT,
    [product_id] INT,
	[status] BIT DEFAULT 1,
    CONSTRAINT [FK_OrderDetail_Order] FOREIGN KEY([order_id]) REFERENCES [dbo].[Order]([id]),
    CONSTRAINT [FK_OrderDetail_Product] FOREIGN KEY([product_id]) REFERENCES [dbo].[Product]([id])
);

-- Chèn dữ liệu vào bảng Role
INSERT INTO [dbo].[Role] ([name]) VALUES ('Admin');
INSERT INTO [dbo].[Role] ([name]) VALUES ('User');

-- Chèn dữ liệu vào bảng User
INSERT INTO [dbo].[User] ([username], [fullname], [password], [address], [phone], [role_id], [create_date], [update_date])
VALUES ('admin', 'Admin', '12345', 'Admin Address', '123456789', 1, GETDATE(), GETDATE());

INSERT INTO [dbo].[User] ([username], [fullname], [password], [address], [phone], [role_id], [create_date], [update_date])
VALUES ('user', 'User', '12345', 'User Address', '987654321', 2, GETDATE(), GETDATE());

-- Chèn dữ liệu vào bảng Category
INSERT INTO [dbo].[Category] ([name]) VALUES ('Toyota');
INSERT INTO [dbo].[Category] ([name]) VALUES ('BMW');

-- Chèn dữ liệu vào bảng Product
INSERT INTO [dbo].[Product] ([name], [price], [title], [color], [image], [stock], [description], [create_date], [update_date], [category_id])
VALUES ('BMW 1', 5000000, 'BMW 3', 'Red', 'https://vcdn-vnexpress.vnecdn.net/2022/04/21/BMW-i7-1-2574-1650510542.jpg',100, 'A description of the car model XYZ', GETDATE(), GETDATE(), 2);

INSERT INTO [dbo].[Product] ([name], [price], [title], [color], [image], [stock], [description], [create_date], [update_date], [category_id])
VALUES ('BMW 5', 5000000, 'BMW 5', 'White', 'https://vcdn-vnexpress.vnecdn.net/2022/04/21/BMW-i7-1-2574-1650510542.jpg',100, 'A description of the car model XYZ', GETDATE(), GETDATE(), 2);

INSERT INTO [dbo].[Product] ([name], [price], [title], [color], [image], [stock], [description], [create_date], [update_date], [category_id])
VALUES ('BMW 6', 5000000, 'BMW 6', 'Pink', 'https://vcdn-vnexpress.vnecdn.net/2022/04/21/BMW-i7-1-2574-1650510542.jpg',100, 'A description of the car model XYZ', GETDATE(), GETDATE(), 2);

INSERT INTO [dbo].[Product] ([name], [price], [title], [color], [image], [stock], [description], [create_date], [update_date], [category_id])
VALUES ('BMW 3', 5000000, 'BMW 3', 'Red', 'https://vcdn-vnexpress.vnecdn.net/2022/04/21/BMW-i7-1-2574-1650510542.jpg',100, 'A description of the car model XYZ', GETDATE(), GETDATE(), 2);

INSERT INTO [dbo].[Product] ([name], [price], [title], [color], [image],[stock], [description], [create_date], [update_date], [category_id])
VALUES ('BMW 2', 5000000, 'BMW 2', 'Red', 'https://tapchi.toyota.com.vn/uploads/images/news/feb23704d32ef6d4.jpg',100, 'A description of the car model XYZ', GETDATE(), GETDATE(), 2);

INSERT INTO [dbo].[Product] ([name], [price], [title], [color], [image],[stock], [description], [create_date], [update_date], [category_id])
VALUES ('Toyota 1', 80000, 'Toyota 1', 'Black', 'https://tapchi.toyota.com.vn/uploads/images/news/feb23704d32ef6d4.jpg',100, 'A description of the smartphone ABC', GETDATE(), GETDATE(), 1);

INSERT INTO [dbo].[Product] ([name], [price], [title], [color], [image],[stock], [description], [create_date], [update_date], [category_id])
VALUES ('Toyota 2', 80000, 'Toyota 2', 'Black', 'https://tapchi.toyota.com.vn/uploads/images/news/feb23704d32ef6d4.jpg',100, 'A description of the smartphone ABC', GETDATE(), GETDATE(), 1);

INSERT INTO [dbo].[Product] ([name], [price], [title], [color], [image],[stock], [description], [create_date], [update_date], [category_id])
VALUES ('Toyota 3', 80000, 'Toyota 3', 'Green', 'https://tapchi.toyota.com.vn/uploads/images/news/feb23704d32ef6d4.jpg', 100, 'A description of the smartphone ABC', GETDATE(), GETDATE(), 1);

INSERT INTO [dbo].[Product] ([name], [price], [title], [color], [image],[stock], [description], [create_date], [update_date], [category_id])
VALUES ('Toyota 4', 80000, 'Toyota 4', 'Blue', 'https://tapchi.toyota.com.vn/uploads/images/news/feb23704d32ef6d4.jpg',100, 'A description of the smartphone ABC', GETDATE(), GETDATE(), 1);

INSERT INTO [dbo].[Product] ([name], [price], [title], [color], [image],[stock], [description], [create_date], [update_date], [category_id])
VALUES ('Toyota 4', 80000, 'Toyota 4', 'Blue', 'https://tapchi.toyota.com.vn/uploads/images/news/feb23704d32ef6d4.jpg',100, 'A description of the smartphone ABC', GETDATE(), GETDATE(), 1);

INSERT INTO [dbo].[Product] ([name], [price], [title], [color], [image],[stock], [description], [create_date], [update_date], [category_id])
VALUES ('Toyota 4', 80000, 'Toyota 4', 'Blue', 'https://tapchi.toyota.com.vn/uploads/images/news/feb23704d32ef6d4.jpg',100, 'A description of the smartphone ABC', GETDATE(), GETDATE(), 1);

